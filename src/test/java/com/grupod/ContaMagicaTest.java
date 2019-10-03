package com.grupod;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContaMagicaTest 
{
    private ContaMagica f;

    @BeforeEach
    public void setup()
    {
        f = new ContaMagica();
    }
    /**
    @DisplayName("Teste INSS")
    @ParameterizedTest
    @CsvSource({"0,0", "1000,45", "2500, 112.50", "2501, 112.54", "100000, 4500", "111111.12, 5000", "200000, 5000"})
    public void getINSS(int val, int resp)
    {
        f.setSalarioBruto(val);
        assertEquals(resp, f.getINSS(), 0.0001);
    }

    @DisplayName("Teste IR")
    @ParameterizedTest
    @CsvSource({"0,0", "2500,0", "2501, 0.12", "100000, 11400", "111111.12, 13033.33", "200000, 23700"})
    public void getIR(int val, int res)
    {
        f.setSalarioBruto(val);
        assertEquals(res, f.getIRPF(), 0.0001);
    }

    @DisplayName("Teste Salario Liquido")
    @ParameterizedTest
    @CsvSource({"0,0", "1000,995", "2500,2387.50", "2501, 2388.34", "100000, 84100","111111.12,93077.79", "200000,171300"})
    public void getSalarioLiquido(int val, int res)
    {
        f.setSalarioBruto(val);
        assertEquals(res, f.getSalarioLiquido(), 0.0001);
    }
    **/
}
