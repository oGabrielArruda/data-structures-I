package ListaDuplamenteLigada;

import java.lang.reflect.Method;

public class ListaDuplamenteLigadaDesordenada<X>
{
    protected class No
    {
        protected X info;
        protected No ante, prox;

        public No(No ante, X info, No prox)
        {
            this.ante = ante;
            this.info = info;
            this.prox = prox;
        }

        public No(X info)
        {
            this.info = info;
            this.prox = null;
            this.ante = null;
        }

        public X getInfo()
        {
            return this.info;
        }

        public No getProx()
        {
            return this.prox;
        }

        public No getAnte()
        {
            return this.ante;
        }

        public void setInfo(X info)
        {
            this.info = info;
        }

        public void setProx(No prox)
        {
            this.prox = prox;
        }

        public void setAnte(No ante)
        {
            this.ante = ante;
        }
    }

    protected No primeiro, ultimo;

    public ListaDuplamenteLigadaDesordenada()
    {
        this.primeiro = this.ultimo = null;
    }

    protected X meuCloneDeX(X x)
    {
        X ret = null;
        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object[] parms = null;
            ret = (X)metodo.invoke(x, parms);
        }
        catch (Exception ex)
        {}

        return ret;
    }

    protected X tryGetCloneable(X info)
    {
        if (info instanceof Cloneable)
            return meuCloneDeX(info);
        return info;
    }

    public void insiraNoInicio(X i) throws Exception
    {
        if (i == null)
            throw new Exception("Informação nula");

        X inserir = this.tryGetCloneable(i);

        if (this.primeiro == null)
        {
            this.primeiro = new No(inserir);
            this.ultimo = this.primeiro;
            return;
        }

        this.primeiro.setAnte(new No(null, inserir, this.primeiro));
        this.primeiro = this.primeiro.getAnte();
    }

    public void insiraNoFim(X i) throws Exception
    {
        if (i == null)
            throw new Exception("Informação nula");

        X inserir = this.tryGetCloneable(i);

        if (this.ultimo == null)
        {
            this.ultimo = new No(inserir);
            this.primeiro = this.ultimo;
            return;
        }

        this.ultimo.setProx(new No(this.ultimo, inserir, null));
        this.ultimo = this.ultimo.getProx();
    }

    public void removaDoInicio() throws Exception
    {
        if (this.primeiro == null)
            throw new Exception("Lista vazia");

        if (this.primeiro == this.ultimo)
        {
            this.primeiro = null;
            this.ultimo = null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
        this.primeiro.setAnte(null);
    }

    public void removaDoFim() throws Exception
    {
        if (this.ultimo == null)
            throw new Exception("Lista vazia");

        if (this.primeiro == this.ultimo)
        {
            this.primeiro = null;
            this.ultimo = null;
            return;
        }

        this.ultimo = this.ultimo.getAnte();
        this.ultimo.setProx(null);
    }

    public void remova(X i) throws Exception
    {
        if (i == null)
            throw new Exception("Informação inválida");

        if (this.primeiro == null)
            throw new Exception("Lista vazia");

        if (i.equals(this.primeiro.getInfo()))
        {
            if (this.primeiro == this.ultimo)
            {
                this.ultimo = null;
                this.primeiro = null;
            } else {
                this.primeiro = this.primeiro.getProx();
                this.primeiro.setAnte(null);
            }
            return;
        }

        No atual = this.primeiro;
        for(;;)
        {
            if(this.primeiro.getProx() == null)
                throw new Exception("Informação insexistente");

            if(i.equals(atual.getProx().getInfo()))
            {
                No novoNo = atual.getProx().getProx();
                atual.setProx(novoNo);
                novoNo.setAnte(atual);
                break;
            }

            atual = atual.getProx();
        }
    }

    public int getQtd()
    {
        int qtd = 0;
        for(No atual = this.primeiro;
            atual != null;
            atual = atual.getProx())
            qtd++;

        return qtd;
    }

    public boolean tem(X i) throws Exception
    {
        if (i == null)
            throw new Exception("Informação inválida");

        for (No atual = this.primeiro; atual != null; atual = atual.getProx())
            if (i.equals(atual.getInfo()))
                return true;

        return false;
    }

    public X getDoInicio() throws Exception
    {
        if (this.primeiro == null)
            throw new Exception("Lista vazia");

        X ret = tryGetCloneable(this.primeiro.getInfo());

        return ret;
    }

    public X getDoFim() throws Exception
    {
        if (this.ultimo == null)
            throw new Exception("Lista vazia");

        X ret = tryGetCloneable(this.ultimo.getInfo());

        return ret;
    }

    public boolean isVazia()
    {
        return this.primeiro == null;
    }

    public String toString()
    {
        String ret = "[ ";
        for(No atual = this.primeiro; atual != null; atual = atual.getProx())
        {
            ret += atual.getInfo() + " ";
        }

        ret += "]";

        return ret;
    }

    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        if (this == obj)
            return true;

        ListaDuplamenteLigadaDesordenada lista =
                (ListaDuplamenteLigadaDesordenada) obj;

        No atualDoThis = this.primeiro;
        No atualDoObj = lista.primeiro;

        while(atualDoObj != null && atualDoThis != null)
        {
            if(!atualDoThis.getInfo().equals(atualDoObj.getInfo()))
                return false;

            atualDoThis = atualDoThis.getProx();
            atualDoObj = atualDoObj.getProx();
        }

        if (atualDoObj != null)
            return false;
        if (atualDoThis != null)
            return false;


        return true;
    }

    public int hashCode()
    {
        int ret = 345;

        for(No atual = this.primeiro; atual != null; atual = atual.getProx())
            ret = ret * 17 + atual.getInfo().hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    public ListaDuplamenteLigadaDesordenada(ListaDuplamenteLigadaDesordenada modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo nulo");

        this.primeiro = new No((X)modelo.primeiro.getInfo());

        No atualDoThis = this.primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while(atualDoModelo != null)
        {
            atualDoThis.setProx(new No(atualDoThis, atualDoModelo.getInfo(), null));

            atualDoThis = atualDoThis.getProx();
            atualDoModelo = atualDoModelo.getProx();
        }

        this.ultimo = atualDoThis;
    }

    public ListaDuplamenteLigadaDesordenada clone()
    {
        ListaDuplamenteLigadaDesordenada ret = null;
        try
        {
            ret = new ListaDuplamenteLigadaDesordenada(this);
        }
        catch (Exception ex)
        {}

        return ret;
    }
}
