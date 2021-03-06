package com.victorseger.svtech.services;

import com.victorseger.svtech.domain.Produto;
import com.victorseger.svtech.repositories.ProdutoRepository;
import com.victorseger.svtech.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;


    public Produto find(Integer id) {
        if (id != null) {
            Optional<Produto> obj = repo.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + ", Tipo: " + Produto.class.getName()));
        }
        return null;
    }

    public List<Produto> findAll() {
        return repo.findAllByOrderByNomeAsc();
    }

    public List<Produto> findWithUnits() {
        List<Produto> lista = repo.findAllByOrderByNomeAsc();
        List<Produto> listaWithUnits = new LinkedList<>();
        for (Produto produto: lista) {
            if(produto.getQtd() > 0) {
                listaWithUnits.add(produto);
            }

        }
        if (listaWithUnits.size()>0) {
            return listaWithUnits;
        } else {
            return null;
        }

    }

    public Produto save(Produto produto) {
        if (produto == null) {
            return null;
        }
        return repo.save(produto);
    }

    public boolean delete(Integer id) {
        boolean flag;
        if (id != null) {
            try {
                if (flag = repo.existsById(id))
                    repo.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                flag = false;
            }
            return flag;
        }
        return false;
    }

//    public boolean haveUnits(Produto produto) {
//
//    }

}