const apiGetUrl = 'http://localhost:8081/api/curso/get';
const apiPostUrl = 'http://localhost:8081/api/curso/post';
const apiPutUrl = 'http://localhost:8081/api/curso/put';
const apiDeleteUrl = 'http://localhost:8081/api/curso/delete';

// Función para crear o actualizar un curso
function createOrUpdate() {
    const id = $('#id').val();
    const nomCurso = $('#nombre_curso').val();
    const descripcion = $('#descripcion').val();

    const data = {
        nomCurso,
        descripcion
    };
    console.log(data)
    if (id) {
        // Si existe un ID, actualizamos el curso
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',

            success: function(response) {
                alert('Curso actualizado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar curso');
            }
        });
    } else {
        // Si no existe un ID, creamos un nuevo curso
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Curso creado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear curso');
            }
        });
    }
}

// Función para cargar los datos de cursos en la tabla
function loadData() {
    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(curso => {
                tableContent += `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.nomCurso}</td>
                        <td>${curso.descripcion}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editCurso(${curso.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteCurso(${curso.id})">Eliminar</button>
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

// Función para editar un curso
function editCurso(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(curso) {
            $('#id').val(curso.id);
            $('#nombre_curso').val(curso.nomCurso);
            $('#descripcion').val(curso.descripcion);
        },
        error: function(error) {
            alert('Error al obtener los datos del curso');
        }
    });
}

// Función para eliminar un curso
function deleteCurso(id) {
    if (confirm('¿Está seguro de que desea eliminar este curso?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Curso eliminado correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar curso');
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
