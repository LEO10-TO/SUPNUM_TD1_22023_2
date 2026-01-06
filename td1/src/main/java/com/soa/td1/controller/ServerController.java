package com.soa.td1.controller;

import com.soa.td1.model.dto.ServerCreateRequest;
import com.soa.td1.model.dto.ServerRenameRequest;
import com.soa.td1.model.entity.Server;
import com.soa.td1.service.ServerSrervice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
public class ServerController {
    
    private final ServerSrervice serverService;
    
    /**
     * Créer un nouveau serveur
     */
    @PostMapping
    public ResponseEntity<Server> createServer(@RequestBody ServerCreateRequest request) {
        Server server = serverService.createServer(request.getName(), request.getIpAddress());
        return ResponseEntity.status(HttpStatus.CREATED).body(server);
    }
    
    /**
     * Lister tous les serveurs
     */
    @GetMapping
    public ResponseEntity<List<Server>> getAllServers() {
        List<Server> servers = serverService.getAllServers();
        return ResponseEntity.ok(servers);
    }
    
    /**
     * Renommer un serveur
     */
    @PutMapping("/{id}/rename")
    public ResponseEntity<Server> renameServer(
            @PathVariable Long id,
            @RequestBody ServerRenameRequest request) {
        Server server = serverService.renameServer(id, request.getNewName());
        return ResponseEntity.ok(server);
    }
    
    /**
     * Récupérer le statut d'un serveur
     */
    @GetMapping("/{id}/status")
    public ResponseEntity<Boolean> getServerStatus(@PathVariable Long id) {
        Boolean status = serverService.getServerStatus(id);
        return ResponseEntity.ok(status);
    }
    
    /**
     * Démarrer un serveur
     */
    @PutMapping("/{id}/start")
    public ResponseEntity<Server> startServer(@PathVariable Long id) {
        Server server = serverService.startServer(id);
        return ResponseEntity.ok(server);
    }
    
    /**
     * Arrêter un serveur
     */
    @PutMapping("/{id}/stop")
    public ResponseEntity<Server> stopServer(@PathVariable Long id) {
        Server server = serverService.stopServer(id);
        return ResponseEntity.ok(server);
    }
    
    /**
     * Supprimer un serveur
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}

