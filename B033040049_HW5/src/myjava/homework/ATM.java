package myjava.homework;
public class ATM {

	private Boolean userAuthenticated;
	private Screen screen;
	private Keypad key;
	private BankDatabase bank = new BankDatabase();	//only create a time
													//or the amount will be refreshed
	
	public void run()
	{
		while(true)				//ATM system never stop
		{
			userAuthenticated = false;
			int account,PIN,doWhat;
			key = new Keypad();
			screen = new Screen();
			
			screen.displayMessageLine("Welcome");
			screen.displayMessageLine("Please enter your account number :");
			account = key.getInput();						//enter account
			screen.displayMessageLine("Enter your PIN :");
			PIN = key.getInput();							//enter PIN
			if(account==-1||PIN==-1)					//wrong account
				userAuthenticated = false;
			else
			{
				for(Account a  : bank.getAccount())		//find the account from the bank database
				{
					if(account == a.getAccountNumber())
					{
						if(a.validatePIN(PIN))			//check if PIN is correct 
						{
							userAuthenticated = true;
						}
					}
				}
			}
			if(!userAuthenticated)
				screen.displayMessageLine("Invalid account or PIN. Please try again.\n");
			if(userAuthenticated)
			{
				while(true)			//user can do things repeatedly
				{
					screen.displayMessageLine("\nMain menu:");
					screen.displayMessageLine("1. View my balance");
					screen.displayMessageLine("2. Withdraw");
					screen.displayMessageLine("3. Deposit");
					screen.displayMessageLine("4. Exit\n");
					screen.displayMessage("Enter a choice:");
					doWhat = key.getInput();
					if(doWhat==1)
					{
						Transaction tran = new BalanceInquiry(account,screen,bank);
						tran.execute();
					}
					else if(doWhat==2)
					{
						Withdrawal withDraw = new Withdrawal(account,screen,bank,key);
						withDraw.execute();
					}
					else if(doWhat==3)
					{
						Deposit deposit= new Deposit(account,screen,bank,key);
						deposit.execute();
					}
					else if(doWhat==4)
					{
						screen.displayMessageLine("EXIT");
						screen.displayMessageLine("Thank you! Goodbye!\n");
						break;
					}
					else
					{
						screen.displayMessageLine("You did not enter a valid selection. Try again.");;
					}
				}
			}
		}
	}
}
