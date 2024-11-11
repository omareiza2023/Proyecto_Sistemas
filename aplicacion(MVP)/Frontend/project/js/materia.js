const apiGetUrl = 'http://localhost:8081/api/materia/get';
const apiPostUrl = 'http://localhost:8081/api/materia/post';
const apiPutUrl = 'http://localhost:8081/api/materia/put';
const apiDeleteUrl = 'http://localhost:8081/api/materia/delete';
// Función para crear o actualizar una materia
function createOrUpdate() {
    const id = $('#id').val();
    const nameMateria = $('#name_materia').val();
    const description = $('#description').val();

    const data = {
        nameMateria,
        description
    };

    if (id) {
        // Si existe un ID, actualizamos la materia
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Materia actualizada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar materia');
            }
        });
    } else {
        // Si no existe un ID, creamos una nueva materia
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Materia creada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear materia');
            }
        });
    }
}

// Función para cargar los datos de materias en la tabla
function loadData() {
    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(materia => {
                tableContent += `
                    <tr>
                        <td>${materia.id}</td>
                        <td>${materia.nameMateria}</td>
                        <td>${materia.description}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editMateria(${materia.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteMateria(${materia.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
            });
            $('#dataBody').html(tableContent);
        },
        error: function(error) {
            alert('Error al cargar los datos');
        }
    });
}

// Función para editar una materia
function editMateria(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(materia) {
            $('#id').val(materia.id);
            $('#name_materia').val(materia.nameMateria);
            $('#description').val(materia.description);
        },
        error: function(error) {
            alert('Error al obtener los datos de la materia');
        }
    });
}

// Función para eliminar una materia
function deleteMateria(id) {
    if (confirm('¿Está seguro de que desea eliminar esta materia?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Materia eliminada correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar materia');
            }
        });
    }
}

// Función para resetear el formulario
function resetForm() {
    $('#crudForm')[0].reset();
    $('#id').val('');
}

// Cargar los datos cuando la página se carga
$(document).ready(function() {
    loadData();
});
