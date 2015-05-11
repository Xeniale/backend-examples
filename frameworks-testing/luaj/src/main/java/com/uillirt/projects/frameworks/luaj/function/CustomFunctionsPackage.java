package com.uillirt.projects.frameworks.luaj.function;

import org.luaj.vm2.core.LuaValue;
import org.luaj.vm2.core.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.Map;

/**
 * Created by kshekhovtsova on 11.05.2015.
 */
public class CustomFunctionsPackage extends TwoArgFunction {

    private Map<String, Object> source;

    public CustomFunctionsPackage() {
    }

    public CustomFunctionsPackage(Map<String, Object> source) {
        this.source = source;
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        env.set("customSrc", new CustomFunctionsPackage.customSrc(source));
        return env;
    }

    static final class customSrc extends VarArgFunction {
        private final Map<String, Object> source;

        public customSrc(Map<String, Object> source) {
            this.source = source;
        }

        public Varargs invoke(Varargs args) {
            String val = args.toString();
            return CoerceJavaToLua.coerce(source.get(val));
        }
    }
}
