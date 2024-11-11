package com.corhuila.planillaescolar.controllers;

import com.corhuila.planillaescolar.entity.Curso;
import com.corhuila.planillaescolar.service.ICursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
public class CursoRestController {

    @Autowired
    private ICursoService cursoService;

    @Operation(summary = "Obtener todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    @GetMapping("/get")

    public List<Curso> index() {
        return cursoService.findAll();
    }

    @Operation(summary = "Obtener curso por ID", description = "Obtiene un curso específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")})
    @GetMapping("/get/{id}")
    public Curso show(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")})
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Curso create(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @Operation(summary = "Actualizar un curso", description = "Actualiza un curso existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")})
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso update(@RequestBody Curso curso, @PathVariable Long id) {
        return cursoService.update(curso, id);
    }

    @Operation(summary = "Eliminar un curso", description = "Elimina un curso existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cursoService.delete(id);
    }
}
