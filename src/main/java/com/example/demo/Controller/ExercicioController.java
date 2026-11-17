
package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class ExercicioController {

    @GetMapping("/hello/{nome}")
    public String hello(@PathVariable String nome) {
        return "Olá, " + nome + "!";
    }



@GetMapping({
    "/calc/soma/{a}/{b}",
    "/calc/soma/{a}",
    "/calc/soma"
})
public ResponseEntity<?> soma(
        @PathVariable(required = false) Integer a,
        @PathVariable(required = false) Integer b) {

    if (a == null || b == null) {
        return ResponseEntity.badRequest()
                .body(Map.of(
                    "erro",
                    "Você precisa informar os dois números: a e b."
                ));
    }

    return ResponseEntity.ok(
            Map.of("resultado", a + b)
    );
}



@GetMapping("/temperatura/{valor}/{de}/{para}")
public ResponseEntity<?> temperatura(
        @PathVariable double valor,
        @PathVariable String de,
        @PathVariable String para) {

    de = de.toUpperCase();
    para = para.toUpperCase();

    if (!de.matches("[CFK]") || !para.matches("[CFK]")) {
        return ResponseEntity.badRequest()
                .body(Map.of(
                    "erro",
                    "Unidades inválidas. Utilize C, F ou K."
                ));
    }

    double celsius;

    switch (de) {
        case "C":
            celsius = valor;
            break;

        case "F":
            celsius = (valor - 32) * 5 / 9;
            break;

        default: 
            celsius = valor - 273.15;
            break;
    }

    double resultado;

    switch (para) {
        case "C":
            resultado = celsius;
            break;

        case "F":
            resultado = (celsius * 9 / 5) + 32;
            break;

        default: 
            resultado = celsius + 273.15;
            break;
    }

    return ResponseEntity.ok(
            Map.of("resultado", resultado)
    );
}

}
