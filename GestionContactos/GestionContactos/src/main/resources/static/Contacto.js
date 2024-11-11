const nombreUsuario = localStorage.getItem('nombreUsuario'); // Nombre de usuario, para mostrar contactos
const usuarioId= localStorage.getItem('usuarioId'); // ID de usuario, para guardar contactos
console.log("usuarioId recuperado de localStorage:", usuarioId);
const apiUrl = 'http://localhost:9000/api/contacto';
const contactosList = document.getElementById('contactosList');
const formularioContacto = document.getElementById('formularioContacto');
const formTitle = document.getElementById('formTitle');
const btnAgregar = document.getElementById('btnAgregar');

let contactoActual = null;

// Cargar contactos cuando el usuario está autenticado
if (nombreUsuario) {
    cargarContactos();
} else {
    contactosList.innerHTML = '<p>No se ha iniciado sesión o no se encuentra el nombre de usuario.</p>';
}

// Función para cargar contactos del usuario
function cargarContactos() {
    fetch(`${apiUrl}?nombreUsuario=${nombreUsuario}`)
        .then(response => response.json())
        .then(contactos => {
            let contactosHTML = '';
            contactos.forEach(contacto => {
                contactosHTML += `
                    <div>
                        <h3>${contacto.nombres} ${contacto.apellidos}</h3>
                        <p>Teléfono: ${contacto.telefono}</p>
                        <button onclick="editarContacto(${contacto.id})">Editar</button>
                        <button onclick="eliminarContacto(${contacto.id})">Eliminar</button>
                    </div>
                `;
            });
            contactosList.innerHTML = contactosHTML;
        })
        .catch(error => {
            console.error('Error al cargar los contactos:', error);
            contactosList.innerHTML = '<p>Error al obtener los contactos.</p>';
        });
}

// Mostrar formulario para agregar nuevo contacto
btnAgregar.addEventListener('click', () => {
    contactoActual = null;  // Reseteamos el contacto actual para indicar que es uno nuevo
    formTitle.innerText = 'Agregar Contacto';
    formularioContacto.style.display = 'block';
});

// Manejar la lógica de guardar contacto (nuevo o editado)
document.getElementById('contactoForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const contacto = {
        nombres: document.getElementById('nombres').value,
        apellidos: document.getElementById('apellidos').value,
        telefono: document.getElementById('telefono').value,
        usuarioId: { id: usuarioId }// Cambiado para enviar el ID del usuario
    };

    if (contactoActual) {
        actualizarContacto(contactoActual.id, contacto);
    } else {
        agregarContacto(contacto);
    }
});

// Agregar nuevo contacto
function agregarContacto(contacto) {
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contacto)
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                console.error('Detalles del error:', errorData);
                throw new Error('Error en la solicitud al agregar el contacto');
            });
        }
        return response.json();
    })
    .then(() => {
        cargarContactos();
        formularioContacto.style.display = 'none';
    })
    .catch(error => console.error('Error al agregar el contacto:', error));
}

// Editar contacto
function editarContacto(id) {
    contactoActual = { id };  // Guardamos el id del contacto a editar
    formTitle.innerText = 'Editar Contacto';
    formularioContacto.style.display = 'block';

    fetch(`${apiUrl}/${id}`)
        .then(response => response.json())
        .then(contacto => {
            document.getElementById('nombres').value = contacto.nombres;
            document.getElementById('apellidos').value = contacto.apellidos;
            document.getElementById('telefono').value = contacto.telefono;
        });
}

// Actualizar contacto
function actualizarContacto(id, contacto) {
    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contacto)
    })
    .then(() => {
        cargarContactos();
        formularioContacto.style.display = 'none';
    })
    .catch(error => console.error('Error al actualizar el contacto:', error));
}

// Eliminar contacto
function eliminarContacto(id) {
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    })
    .then(() => cargarContactos())
    .catch(error => console.error('Error al eliminar el contacto:', error));
}

// Cancelar y ocultar el formulario
document.getElementById('cancelButton').addEventListener('click', () => {
    formularioContacto.style.display = 'none';
});
