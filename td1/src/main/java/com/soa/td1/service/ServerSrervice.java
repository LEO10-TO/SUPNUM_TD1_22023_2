package com.soa.td1.service;

import com.soa.td1.model.entity.Server;
import com.soa.td1.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServerSrervice {
    
    private final ServerRepository serverRepository;
    
    public Server createServer(String name, String ipAddress) {
        Server server = new Server();
        server.setName(name);
        server.setIpAddress(ipAddress);
        server.setStatus(false); 
        return serverRepository.save(server);
    }
    
    
    @Transactional(readOnly = true)
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }
    
    
    public Server renameServer(Long id, String newName) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID: " + id));
        server.setName(newName);
        return serverRepository.save(server);
    }
    
    
    @Transactional(readOnly = true)
    public Boolean getServerStatus(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID: " + id));
        return server.getStatus();
    }
    
    
    public Server startServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID: " + id));
        server.setStatus(true);
        return serverRepository.save(server);
    }
    
  
    public Server stopServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID: " + id));
        server.setStatus(false);
        return serverRepository.save(server);
    }
    
    
    public void deleteServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serveur non trouvé avec l'ID: " + id));
        
        if (Boolean.TRUE.equals(server.getStatus())) {
            throw new RuntimeException("Impossible de supprimer un serveur en cours d'exécution");
        }
        
        serverRepository.delete(server);
    }
}
