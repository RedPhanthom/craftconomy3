/*
 * This file is part of Craftconomy3.
 *
 * Copyright (c) 2011-2014, Greatman <http://github.com/greatman/>
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
package com.greatmancode.craftconomy3.storage;

import com.greatmancode.craftconomy3.Common;
import com.greatmancode.craftconomy3.storage.sql.H2Engine;
import com.greatmancode.craftconomy3.storage.sql.MySQLEngine;

/**
 * Created by greatman on 2014-07-13.
 */
public class StorageHandler {

    private final StorageEngine engine;

    public StorageHandler() {
        System.out.println(Common.getInstance().getMainConfig().getString("System.Database.Type"));
        switch (Common.getInstance().getMainConfig().getString("System.Database.Type")) {
            case "h2":
                engine = new H2Engine();
                System.out.println("DID H2");
                break;
            case "mysql":
                engine = new MySQLEngine();
                System.out.println("DID MYSQL");
                break;
            default:
                engine = null;
                Common.getInstance().getLogger().severe("Storage engine not supported!");
                break;
        }
    }

    public StorageEngine getStorageEngine() {
        return engine;
    }

    public void disable() {
        engine.disable();
    }


}
