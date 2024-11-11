package com.corhuila.planillaescolar.controllers;

import com.corhuila.planillaescolar.entity.Materia;
import com.corhuila.planillaescolar.service.IMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia")
@CrossOrigin(origins = "*")
public class MateriaRestController {

    @Autowired
    private IMateriaService materiaService;

    @Operation(summary = "Obtener todas las materias", description = "Devuelve una lista con todas las materias disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de materias obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    @GetMapping("/get")
    public List<Materia> index(){
        return materiaService.findAll();
    }

    @Operation(summary = "Obtener una materia por ID", description = "Devuelve una materia específica mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materia encontrada"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")})
    @GetMapping("/get/{id}")
    public Materia show(@PathVariable Long id){
        return materiaService.findById(id);
    }

    @Operation(summary = "Crear una nueva materia", description = "Permite crear una nueva materia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Materia creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")})
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Materia create(@RequestBody Materia paisaje){
        return materiaService.save(paisaje);
    }

    @Operation(summary = "Actualizar una materia", description = "Actualiza una materia existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materia actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")})
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Materia update(@RequestBody Materia materia, @PathVariable Long id){
        return materiaService.update(materia, id);
    }

    @Operation(summary = "Eliminar una materia", description = "Elimina una materia existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Materia eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        materiaService.delete(id);
    }
}
