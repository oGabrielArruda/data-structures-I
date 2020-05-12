package ListaSimples;

public class ListaSimplesDesoSemRepeticao<X> extends ListaSimplesDesordenada<X>
{
    public ListaSimplesDesoSemRepeticao()
    {
        super();
    }
    public ListaSimplesDesoSemRepeticao(ListaSimplesDesoSemRepeticao modelo) throws Exception
    {
        super(modelo);
    }
    public Object clone()
    {
        ListaSimplesDesoSemRepeticao ret = null;
        try
        {
            ret = new ListaSimplesDesoSemRepeticao<X>(this);
        }
        catch (Exception ex)
        {}
        return ret;
    }

    public void inserirNoInicio(X x) throws Exception
    {
        if(super.tem(x))
            throw new Exception("Já existe");
        super.insiraNoInicio(x);
    }

    public void inserirNoFim(X x) throws Exception
    {
        if(super.tem(x))
            throw new Exception("Já existe");
        super.insiraNoFim(x);
    }

}
