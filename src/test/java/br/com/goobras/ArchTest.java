package br.com.goobras;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.goobras");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.goobras.service..")
            .or()
            .resideInAnyPackage("br.com.goobras.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.goobras.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
