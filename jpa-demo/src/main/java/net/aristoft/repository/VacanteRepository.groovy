package net.aristoft.repository

import net.aristoft.model.Vacante
import org.springframework.data.jpa.repository.JpaRepository

interface VacanteRepository extends JpaRepository<Vacante,Integer> {

    List<Vacante> findByEstatus(String estatus)
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus)
    List<Vacante> findBySalarioBetweenOrderBySalarioAsc(double s1, double s2)
    List<Vacante>  findByEstatusIn(String[] estatus);
}