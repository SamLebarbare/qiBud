package controllers;

import java.util.List;

import play.mvc.Controller;

import budstore.Bud;

public class BudsController
        extends Controller
{

    public static void buds()
    {
        List<Bud> buds = Bud.findAll();
        todo();
    }

    public static void budCreateForm()
    {
        todo();
    }

    public static void saveNewBud()
    {
        todo();
    }

    public static void bud( String identity )
    {
        todo();
    }

    public static void budEditForm( String identity )
    {
        todo();
    }

    public static void saveBud( String identity )
    {
        todo();
    }

    public static void deleteBud( String identity )
    {
        todo();
    }

    public static void budByRole( String identity, String pack, String role )
    {
        todo();
    }

    public static void budEditFormByRole( String identity, String pack, String role )
    {
        todo();
    }

    public static void saveBudByRole( String identity, String pack, String role )
    {
        todo();
    }

    public static void deleteBudByRole( String identity, String pack, String role )
    {
        todo();
    }

    public static void roleActionForm( String identity, String pack, String role, String action )
    {
        todo();
    }

    public static void invokeRoleAction( String identity, String pack, String role, String action )
    {
        todo();
    }

}
