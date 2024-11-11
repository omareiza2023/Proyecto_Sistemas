const apiGetUserUrl = 'http://localhost:8081/api/usuario/get';
const apiGetMateriasUrl = 'http://localhost:8081/api/materia/get';
const apiGetNotasUrl = 'http://localhost:8081/api/nota/get';

// Función para cargar la información del estudiante, curso, materias y notas
function loadStudentData(userId) {
    // Obtener datos del usuario (nombre y curso)
    $.ajax({
        url: `${apiGetUserUrl}/${userId}`,
        type: 'GET',
        success: function(usuario) {
            $('#studentName').text(`Nombre del Estudiante: ${usuario.username}`);
            $('#courseName').text(`Curso: ${usuario.curso.nomCurso}`);
            
            // Obtener materias y notas del usuario
            loadMateriasAndNotas(userId);
        },
        error: function(error) {
            alert('Error al cargar los datos del usuario');
        }
    });
}

// Función para cargar las materias y notas del usuario
function loadMateriasAndNotas(userId) {
    $.ajax({
        url: apiGetNotasUrl,
        type: 'GET',
        success: function(notas) {
            let tableContent = '';
            notas.forEach(nota => {
                if (nota.usuario_id === userId) {
                    const definitiva = ((nota.corte1 + nota.corte2 + nota.corte3) / 3).toFixed(2);
                    tableContent += `
                        <tr>
                            <td>${nota.materia.nameMateria}</td>
                            <td>${nota.corte1}</td>
                            <td>${nota.corte2}</td>
                            <td>${nota.corte3}</td>
                            <td>${definitiva}</td>
                        </tr>
                    `;
                }
            });
            $('#materiasBody').html(tableContent);
        },
        error: function(error) {
            alert('Error al cargar las materias y notas');
        }
    });
}

// Llamada inicial para cargar los datos cuando la página se carga
$(document).ready(function() {
    const userId = 1; // Aquí puedes reemplazar con el ID del usuario actual
    loadStudentData(userId);
});
