package com.lab5.lab5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BankAccountTest {
	BankAccount ba;

	@BeforeEach
	public void setUp()
	{
	 ba = new BankAccount(1000.00);
	}
	
	@Test
	public void testInitialBalance() 
	{
		double saldo = ba.getBalance();
		assertEquals(1000.00, saldo);
	}
	
	@Test
	public void testDeposit() 
	{
		ba.deposit(500.00);
		double saldo = ba.getBalance();
		assertEquals(1500.00, saldo);
	}
	
	@Test
	public void testWithdraw() throws InsufficientFundsException 
	{
		ba.withdraw(200.00);
		double saldo = ba.getBalance();
		assertEquals(800.00, saldo);
	}
	
	@Test
	public void testWithdrawInsufficientFunds() 
	{
	
		InsufficientFundsException ex = assertThrows(InsufficientFundsException.class,
				() -> ba.withdraw(1500.00));
    	assertEquals("Insufficient funds", ex.getMessage());
	}
	
	@Test
	public void testMultipleDeposits() 
	{
		ba.deposit(200.00);
		ba.deposit(300.00);
		double saldo = ba.getBalance();
		assertEquals(1500.00, saldo);
	}
	
	@Test
	public void testMultipleWithdrawals() throws InsufficientFundsException 
	{
		ba.withdraw(100.00);
		ba.withdraw(50.00);
		double saldo = ba.getBalance();
		assertEquals(850.00, saldo);
		
	}
	@Test
	public void testDepositAndWithdraw() throws InsufficientFundsException 
	{
		ba.deposit(200.00);
		ba.withdraw(50.00);
		double saldo = ba.getBalance();
		assertEquals(1150.00, saldo);
	}
	
	@Test
	public void testNegativeDeposit() 
	{
		ba.deposit(-100.00);
		double saldo = ba.getBalance();
		assertEquals(1000.00, saldo);
	}

}
