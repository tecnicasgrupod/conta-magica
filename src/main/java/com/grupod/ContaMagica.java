package com.grupod;

public class ContaMagica implements IContaMagica{

    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;
    
    private double saldo;
    private int status;
    
    public ContaMagica() {
       this.saldo = 0.0;
       this.status = 0;     
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void deposito(int valor) {
        if(getStatus() == 0){
            double novoSaldo = getSaldo() + valor;
            this.saldo = novoSaldo;
        }else if(getStatus() == 1){
            double novoSaldo = getSaldo() + valor + (valor * 0.01);
            this.saldo = novoSaldo;
        }else if(getStatus() == 2){
            double novoSaldo = getSaldo() + valor + (valor * 0.025);
            this.saldo = novoSaldo;
        }

        if(getStatus() == 0){
            if(getSaldo() >= 50000){
                this.status = 1;
            }
        }

        else if(getStatus() == 1){
            if(getSaldo() >= 200000){
                this.status = 2;
            }
        }
        
    }

    @Override
    public void retirada(int valor) {
        //valores negativos
        if(getSaldo() >= valor){
            double novoSaldo = getSaldo() - valor;
            this.saldo = novoSaldo;        
        }else{
            //saldo
        }

        if(getStatus() == 2){
            if(getSaldo() < 100000){
                this.status = 1;
            }
        }

        else if(getStatus() == 1){
            if(getSaldo() < 25000){
                this.status = 0;
            }
        }

    }

}
