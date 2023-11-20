package co.empresa.proyectoWeb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento implements Serializable {

    private Integer id;
    private Date dateBill;
    private Integer userId;
    private BigDecimal value;
    private Integer type;
    private String observation;

    // Otros campos según sea necesario

    // Constructor y métodos getter/setter

}
