package com.notifyandwait;

class Customer extends Thread
{  
	int amount=10000;  
  
	synchronized void withdraw(int amount)
	{  
		System.out.println("Going to withdraw... 15,000");
		System.out.println("Yours balance is " + this.amount);  
  
		if(this.amount<amount)
		{  
			System.out.println("Low balance; waiting for deposit...");  
			try
			{
				wait();
				//goes to deposit and notify()
				// waiting for deposit
			}
			catch(Exception e)
			{
				//handling
			}  
		}  
		this.amount-=amount;  
		System.out.println("Withdraw completed...(15,000)");
		System.out.println("Now your balance is " + this.amount);
	}  
  
	synchronized void deposit(int amount)
	{  
		System.out.println("Going to deposit... 10,000");  
		this.amount+=amount;  
		System.out.println("Deposit completed... ");
		System.out.println("Now your balance is " + this.amount);
		notify();
		// after deposit goes to withdraw
		// after deposit gives notification on wait() withdraw
	}  
}  
  
class Test extends Thread
{  
	public static void main(String args[])
	{   
		final Customer c = new Customer();  
		c.start();
		
		//withdraw
		new Thread()
		{  
			public void run()
			{
				c.withdraw(15000);
			}  
		}.start();
		
		//deposit
		new Thread()
		{  
			public void run()
			{
				c.deposit(10000);
			}  
		}.start(); 
  
	}
}

