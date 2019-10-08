package com.grupod;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContaMagicaTest {
    private ContaMagica myAccount;

    @BeforeEach
    public void setup() {
        myAccount = new ContaMagica();
    }

    /** Teste de exceções */

    @Test
    public void testeRetiradaNegativa() {
        Assertions.assertThrows(INVALID_OPER_EXCEPTION.class, () -> myAccount.retirada(-100));
    }

    @Test
    public void testeDepositoNegativo() {
        Assertions.assertThrows(INVALID_OPER_EXCEPTION.class, () -> myAccount.deposito(-100));
    }

    @Test
    public void testeRetiradaSemSaldo() {
        Assertions.assertThrows(INVALID_OPER_EXCEPTION.class, () -> myAccount.retirada(50000));
    }

    /** Testes de status da conta */

    @DisplayName("Teste conta Silver")
    @ParameterizedTest
    @CsvSource({ "0", "5000", "49999" })
    public void testeStatusContaSilver(int valor) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(valor);
        assertEquals(0, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste conta Gold")
    @ParameterizedTest
    @CsvSource({ "50000", "100000", "199999", "500000" })
    public void testeStatusContaGold(int valor) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(valor);
        assertEquals(1, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste conta Platinum")
    @ParameterizedTest
    @CsvSource({ "200000", "250000", "300000", "500000" })
    public void testeStatusContaPlatinum(int valor) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.deposito(valor);
        assertEquals(2, myAccount.getStatus(), 0.0001);
    }

    /** Testes de troca de status */

    @DisplayName("Teste conta Platinum para Gold")
    public void testaContaPlatinumParaGold() throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.deposito(150000);
        myAccount.retirada(200000);
        assertEquals(1, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste conta Platinum para Silver")
    public void testaContaPlatinumParaSilver() throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.deposito(150000);
        myAccount.retirada(150000);
        myAccount.retirada(100);
        assertEquals(0, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste conta Gold para Silver")
    public void testaContaGoldParaSilver() throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.retirada(50000);
        assertEquals(0, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste mantem Gold")
    public void testaMantemContaGold() throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.retirada(25000);
        assertEquals(1, myAccount.getStatus(), 0.0001);
    }

    @DisplayName("Teste mantem Platinum")
    public void testaMantemContaPlatinum() throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(200000);
        myAccount.deposito(201000);
        myAccount.retirada(101000);
        assertEquals(2, myAccount.getStatus(), 0.0001);
    }

    /** Teste Depositos */

    @DisplayName("Teste deposito Silver")
    @ParameterizedTest
    @CsvSource({ "0,0", "1000,1000", "30000,30000 ", "51000, 51000" })
    public void testeDepositoSilver(int valor, int res) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(valor);
        assertEquals(res, myAccount.getSaldo(), 0.0001);
    }

    @DisplayName("Teste deposito Gold")
    @ParameterizedTest
    @CsvSource({ "0,50000", "1000,51010", "30000,80300 ", "201000, 253010" })
    public void testeDepositoGold(int valor, int res) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(50000);
        myAccount.deposito(valor);
        assertEquals(res, myAccount.getSaldo(), 0.0001);
    }

    @DisplayName("Teste deposito Platinum")
    @ParameterizedTest
    @CsvSource({ "0,200000", "1000,201025", "30000,230750 ", "500000, 712500" })
    public void testeDepositoPlatinum(int valor, int res) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(200000);
        myAccount.deposito(100);
        myAccount.retirada(101);
        myAccount.deposito(valor);
        assertEquals(res, myAccount.getSaldo(), 0.0001);
    }

    /** Teste Retiradas */

    @DisplayName("Teste Saque")
    @ParameterizedTest
    @CsvSource({ "0,100000", "0,100000", "100000, 0", "50000, 50000" })
    public void testeSaque(int valor, int res) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(100000);
        myAccount.retirada(valor);
        assertEquals(res, myAccount.getSaldo(), 0.0001);
    }

    @DisplayName("Teste Saque Platina")
    @ParameterizedTest
    @CsvSource({ "201000, 0", "50000, 151000" })
    public void testeSaquePlatina(int valor, int res) throws INVALID_OPER_EXCEPTION {
        myAccount.deposito(100000);
        myAccount.deposito(100000);
        myAccount.retirada(valor);
        assertEquals(res, myAccount.getSaldo(), 0.0001);
    }

}
