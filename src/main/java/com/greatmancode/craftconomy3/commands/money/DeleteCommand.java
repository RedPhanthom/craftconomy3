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
package com.greatmancode.craftconomy3.commands.money;

import com.greatmancode.craftconomy3.Common;
import com.greatmancode.craftconomy3.commands.CraftconomyCommand;
import com.greatmancode.craftconomy3.database.tables.AccountTable;
import com.greatmancode.craftconomy3.database.tables.BalanceTable;

public class DeleteCommand implements CraftconomyCommand {

	@Override
	public void execute(String sender, String[] args) {
		if (Common.getInstance().getAccountManager().exist(args[0])) {
			AccountTable account = Common.getInstance().getDatabaseManager().getDatabase().select(AccountTable.class).where().contains("name", args[0]).execute().findOne();
			Common.getInstance().getDatabaseManager().getDatabase().remove(Common.getInstance().getDatabaseManager().getDatabase().select(BalanceTable.class).where().contains("username_id", account.id).execute().find());
			Common.getInstance().getDatabaseManager().getDatabase().remove(account);
			Common.getInstance().getServerCaller().sendMessage(sender, "{{GREEN}}The account {{WHITE}}" + args[0] + " {{GREEN}}has been deleted!");
		} else {
			Common.getInstance().getServerCaller().sendMessage(sender, "{{RED}}This account doesn't exist!");
		}
	}

	@Override
	public boolean permission(String sender) {
		return Common.getInstance().getServerCaller().checkPermission(sender, "craftconomy.account.delete");
	}

	@Override
	public String help() {
		return "/money delete <Name> - Delete a account";
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
