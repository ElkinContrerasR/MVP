const apiUrl = 'http://localhost:9000/api/usuario/login';

document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const usuario = {
        nombreUsuario: document.getElementById('nombreUsuario').value,
        contrasenia: document.getElementById('contrasenia').value
    };

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('loginMessage').innerText = "Login exitoso";
            
            localStorage.setItem('nombreUsuario', usuario.nombreUsuario); // Guardamos el nombre de usuario

            // Redirigir a la página de contactos
            setTimeout(() => {
                window.location.href = 'Contacto.html';  // Redirige a la página de contactos
            }, 1000);
        } else {
            document.getElementById('loginMessage').innerText = "Credenciales incorrectas";
        }
        clearForm();
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
        document.getElementById('loginMessage').innerText = "Error en la comunicación con el servidor";
    });
});

// Función para limpiar el formulario
function clearForm() {
    document.getElementById('loginForm').reset();
}
