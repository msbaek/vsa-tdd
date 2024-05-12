package com.example.vsatdd;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class VsaTddApplicationTests {
    ApplicationModules modules = ApplicationModules.of(VsaTddApplication.class);


    @Test
    void verifyPackageConformity() {
        modules.verify();
    }

    @Test
    void createModulithsDocumentation() {
        new Documenter(modules).writeDocumentation();
    }
}