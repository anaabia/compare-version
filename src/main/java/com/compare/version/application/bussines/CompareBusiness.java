package com.compare.version.application.bussines;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompareBusiness {

    public Integer compareVersion(@RequestParam("version1") String version1, @RequestParam("version2") String version2) {
        List<Integer> arrVersion1 = Arrays.asList(version1.split("\\.")).stream().mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
        List<Integer> arrVersion2 = Arrays.asList(version2.split("\\.")).stream().mapToInt(Integer::valueOf).boxed().limit(arrVersion1.size()).collect(Collectors.toList());

        try {
            for (int i = 0; i < arrVersion1.size(); i++) {
                if (arrVersion1.get(i) == arrVersion2.get(i)){
                    continue;
                } else if (arrVersion1.get(i) > arrVersion2.get(i)) {
                    return 1;
                } else if (arrVersion1.get(i) < arrVersion2.get(i)){
                    return -1;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            return arrVersion1.size() > arrVersion2.size() ? 1 : 0;
        }

        return version2.split("\\.").length > arrVersion1.size() ? -1 : 0;
    }
}

