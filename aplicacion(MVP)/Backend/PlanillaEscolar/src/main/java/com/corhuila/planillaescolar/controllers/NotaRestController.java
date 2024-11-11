package com.corhuila.planillaescolar.controllers;

import com.corhuila.planillaescolar.entity.Nota;
import com.corhuila.planillaescolar.service.INotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*")
public class NotaRestController {

    @Autowired
    private INotaService notaService;

    @Operation(summary = "Obtener todas las notas", description = "Retorna una lista de todas las notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notas obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/get")
    public List<Nota> getAllNotas() {
        return notaService.findAll();
    }

    @Operation(summary = "Obtener una nota por ID", description = "Retorna una nota específica basado en el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada")
    })
    @GetMapping("/get/{id}")
    public Nota getNotaById(@PathVariable Long id) {
        return notaService.findById(id);
    }

    @Operation(summary = "Crear una nueva nota", description = "Crea y guarda una nueva nota en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Nota createNota(@RequestBody Nota nota) {
        return notaService.save(nota);
    }

    @Operation(summary = "Actualizar una nota existente", description = "Actualiza los detalles de una nota existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada")
    })
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Nota updateNota(@RequestBody Nota nota, @PathVariable Long id) {
        return notaService.update(nota, id);
    }

    @Operation(summary = "Eliminar una nota", description = "Elimina una nota de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nota eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Nota no encontrada")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNota(@PathVariable Long id) {
        notaService.delete(id);
    }
}
