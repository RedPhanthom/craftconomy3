/*
 * This file is part of Craftconomy3.
 *
 * Copyright (c) 2011-2012, Greatman <http://github.com/greatman/>
 *
 * Craftconomy3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Craftconomy3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Craftconomy3.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.craftconomy3.commands.bank;

import java.util.Iterator;

import com.greatmancode.craftconomy3.Common;
import com.greatmancode.craftconomy3.account.Account;
import com.greatmancode.craftconomy3.account.Balance;
import com.greatmancode.craftconomy3.commands.CraftconomyCommand;

public class BankBalanceCommand implements CraftconomyCommand {

	@Override
	public void execute(String sender, String[] args) {
		if (Common.getInstance().getAccountHandler().exist(Account.BANK_PREFIX + args[0])) {
			Account account = Common.getInstance().getAccountHandler().getAccount(Account.BANK_PREFIX + args[0]);
			if (account.getAccountACL().canShow(sender) || Common.getInstance().getServerCaller().checkPermission(sender, "craftconomy.bank.balance.others")) {
				Common.getInstance().getServerCaller().sendMessage(sender, "{{GREEN}}Bank Statement:");
				Iterator<Balance> balanceList = account.getAllBalance().iterator();
				while (balanceList.hasNext()) {
					Balance bl = balanceList.next();
					Common.getInstance().getServerCaller().sendMessage(sender, Common.getInstance().format(bl.getWorld(), bl.getCurrency(), bl.getBalance()));

				}
			} else {
				Common.getInstance().getServerCaller().sendMessage(sender, "{{RED}}You can't check this account statement");
			}
		} else {
			Common.getInstance().getServerCaller().sendMessage(sender, "{{RED}}This account doesn't exist!");
		}
	}

	@Override
	public boolean permission(String sender) {
		return Common.getInstance().getServerCaller().checkPermission(sender, "craftconomy.bank.balance");
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "/bank balance <Account Name> - Check the balance of a account.";
	}

	@Override
	public int maxArgs() {
		return 1;
	}

	@Override
	public int minArgs() {
		return 1;
	}

	@Override
	public boolean playerOnly() {
		return false;
	}

}