package com.uillirt.projects.frameworks.jacorb;

import org.jacorb.orb.ORB;

/**
 * Created by kshekhovtsova on 23.07.2015.
 */
public class JacorbTest {

    public static void main(String[] args) {
        ORB orb = new ORB();
       // org.omg.CORBA.Object object = new org.omg.CORBA.Object();
        org.omg.CORBA.Object object = orb.string_to_object("IOR:000000000000001349444C3A41646D696E4167656E743A312E30000000000001000000000000011000010200000000103139322E3136382E3137352E31373000000000000000004C5374616E64617264496D706C4E616D652F44522D434D44424C413030335F69756D5F70657273697374656E745F706F612F5349555F44522D434D44424C413030335F41646D696E4167656E740000000400000014000000080000007E007E09BF0000000000000008000000004A414300000000010000001C0000000000010001000000010501000100010109000000010501000100000021000000540001000000000001007E000000000024000000220000007E007E000000000001000000103139322E3136382E3137352E3137300009BF000000000000000000000000000004000000000000000000000000000004");
        System.out.print(object);
    }
}
