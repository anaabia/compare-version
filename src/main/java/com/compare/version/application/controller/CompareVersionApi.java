package com.compare.version.application.controller;

import com.compare.version.application.bussines.CompareBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompareVersionApi {

    @Autowired
    CompareBusiness compareBusiness;

    @GetMapping("/compare")
    ResponseEntity<Integer> compareVersion(@RequestParam("version1") String version1, @RequestParam("version2") String version2) {
        return ResponseEntity.ok(compareBusiness.compareVersion(version1, version2));
    }


}
