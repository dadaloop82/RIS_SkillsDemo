package com.dadaloop.RISSkillDemo.controller;

import com.dadaloop.RISSkillDemo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map; 
import java.util.HashMap;

/**
 * Controller class for handling database information requests.
 */
@RestController
@RequestMapping("/api/db-info")
public class DBInfoController {

  @Autowired
  private NoteService noteService;

  /**
   * Retrieves database information including connection status and record count.
   *
   * @return A ResponseEntity containing database information and HTTP status.
   */
  @GetMapping
  public ResponseEntity<Map<String, Object>> getDBInfo() { 
    try {
      boolean isConnected = noteService.checkDBConnection();
      int recordCount = noteService.getRecordCount(); 

      Map<String, Object> info = new HashMap<>();
      info.put("connectionStatus", isConnected ? "Connected" : "Disconnected");
      info.put("recordCount", recordCount);

      return new ResponseEntity<>(info, HttpStatus.OK); 
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
