const apiUrl = 'http://localhost:9000/api/usuario';  // Ruta para el endpoint que maneja el registro

document.getElementById('registerForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const usuario = {
        nombres: document.getElementById('nombres').value,
        apellidos: document.getElementById('apellidos').value,
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
            return response.json();  // Obtener la respuesta JSON
        } else {
            throw new Error("Error al registrar el usuario");
        }
    })
    .then(data => {
        // Aquí asumimos que `data` contiene el `usuarioId`
        if (data && data.id) {
            localStorage.setItem('usuarioId', data.id);  // Guarda el ID en localStorage
            document.getElementById('registerMessage').innerText = "Registro exitoso. Puedes iniciar sesión.";

            // Opcional: Redirigir a la página de login después de registrarse
            setTimeout(() => {
                window.location.href = 'Usuario.html';  // Redirige al login después de 2 segundos
            }, 2000);
        }
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
        document.getElementById('registerMessage').innerText = "Error en la comunicación con el servidor.";
    });
});
