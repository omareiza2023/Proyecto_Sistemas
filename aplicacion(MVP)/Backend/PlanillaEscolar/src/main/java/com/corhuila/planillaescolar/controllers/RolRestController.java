package com.corhuila.planillaescolar.controllers;


import com.corhuila.planillaescolar.entity.Rol;
import com.corhuila.planillaescolar.service.IRolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*")
public class RolRestController {

    @Autowired
    private IRolService rolService;

    @Operation(summary = "Obtener todos los roles", description = "Devuelve una lista de todos los roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles encontrados exitosamente")
    })
    @GetMapping("/get")
    public List<Rol> findAll() {
        return rolService.findAll();
    }

    @Operation(summary = "Obtener un rol por ID", description = "Devuelve un rol basado en el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/get/{id}")
    public Rol findById(@PathVariable Long id) {
        return rolService.findById(id);
    }

    @Operation(summary = "Crear un nuevo rol", description = "Agrega un nuevo rol a la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol save(@RequestBody Rol rol) {
        return rolService.save(rol);
    }

    @Operation(summary = "Actualizar un rol", description = "Actualiza los datos de un rol existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rol update(@RequestBody Rol rol, @PathVariable Long id) {
        return rolService.update(rol, id);
    }

    @Operation(summary = "Eliminar un rol", description = "Elimina un rol de la base de datos basado en el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rol eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rolService.delete(id);
    }
}
