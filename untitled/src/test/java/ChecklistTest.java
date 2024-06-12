import org.example.Checklist;
import org.example.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ChecklistTest {

private Checklist checklist;

@BeforeEach
public void setup() {
        checklist = new Checklist();
    }

@Test
    public void testBuscarTodasAsTarefas() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Ir ao mercado");

        checklist.addTarefa(tarefa);
        List<Tarefa> listas = checklist.getTodasAsTarefas();

        assertEquals(1, listas.size());
        assertEquals("Ir ao mercado", listas.getFirst().getDescricao());
        assertFalse(listas.getFirst().isConcluida());
    }

@Test
public void testBuscarTodasAsTarefasConcluidas() {
    Tarefa tarefaNaoConcluida = new Tarefa();
    tarefaNaoConcluida.setDescricao("Ir ao mercado");

    Tarefa tarefaConcluida = new Tarefa();
    tarefaConcluida.setDescricao("Lavar o carro");
    tarefaConcluida.setConcluida(true);

    checklist.addTarefa(tarefaNaoConcluida);
    checklist.addTarefa(tarefaConcluida);

    List<Tarefa> listas = checklist.getTodasAsTarefasConcluidas();

    assertEquals(1, listas.size());
    assertEquals("Lavar o carro", listas.getFirst().getDescricao());
    assertTrue(listas.getFirst().isConcluida());
}

@Test
public void testBuscarTodasAsTarefasPendentes() {
    Tarefa tarefaNaoConcluida = new Tarefa();
    tarefaNaoConcluida.setDescricao("Ir ao mercado");

    Tarefa tarefaConcluida = new Tarefa();
    tarefaConcluida.setDescricao("Lavar o carro");
    tarefaConcluida.setConcluida(true);

    checklist.addTarefa(tarefaNaoConcluida);
    checklist.addTarefa(tarefaConcluida);

    List<Tarefa> listas = checklist.getTodasAsTarefasPendentes();

    assertEquals(1, listas.size());
    assertEquals("Ir ao mercado", listas.getFirst().getDescricao());
    assertFalse(listas.getFirst().isConcluida());
}

@Test
public void testMarcarTarefaComoConcluida() {
    Tarefa tarefa1 = new Tarefa();
    tarefa1.setId(1);
    tarefa1.setDescricao("Ir ao mercado");

    Tarefa tarefa2 = new Tarefa();
    tarefa2.setId(2);
    tarefa2.setDescricao("Lavar o carro");

    checklist.addTarefa(tarefa1);
    checklist.addTarefa(tarefa2);
    checklist.alterarStatusTarefa(1, true);

    List<Tarefa> listas = checklist.getTodasAsTarefas();

    assertEquals(2, listas.size());

    assertEquals("Ir ao mercado", listas.getFirst().getDescricao());
    assertTrue(listas.getFirst().isConcluida());

    assertEquals("Lavar o carro", listas.getLast().getDescricao());
    assertFalse(listas.getLast().isConcluida());
}

@Test
public void testDesmarcarTarefaComoConcluida() {
    Tarefa tarefa1 = new Tarefa();
    tarefa1.setId(1);
    tarefa1.setDescricao("Ir ao mercado");
    tarefa1.setConcluida(true);

    Tarefa tarefa2 = new Tarefa();
    tarefa2.setId(2);
    tarefa2.setDescricao("Lavar o carro");
    tarefa2.setConcluida(true);

    checklist.addTarefa(tarefa1);
    checklist.addTarefa(tarefa2);
    checklist.alterarStatusTarefa(2, false);

    List<Tarefa> listas = checklist.getTodasAsTarefas();

    assertEquals(2, listas.size());

    assertEquals(1, listas.getFirst().getId());
    assertEquals("Ir ao mercado", listas.getFirst().getDescricao());
    assertTrue(listas.getFirst().isConcluida());

    assertEquals(2, listas.getLast().getId());
    assertEquals("Lavar o carro", listas.getLast().getDescricao());
    assertFalse(listas.getLast().isConcluida());
}

@Test
public void testBuscarTodasAsDescricoes() {
    Tarefa tarefa1 = new Tarefa();
    tarefa1.setDescricao("Ir ao mercado");

    Tarefa tarefa2 = new Tarefa();
    tarefa2.setDescricao("Lavar o carro");

    checklist.addTarefa(tarefa1);
    checklist.addTarefa(tarefa2);
    List<String> descricoes = checklist.getTodasAsDescricoes();

    assertEquals(2, descricoes.size());
    assertEquals("Ir ao mercado", descricoes.getFirst());
    assertEquals("Lavar o carro", descricoes.getLast());
}

@Test
public void testOrdenarListaPorConclusaoEDescricao() {
    Tarefa tarefa1 = new Tarefa();
    tarefa1.setDescricao("Ir ao mercado");
    tarefa1.setConcluida(true);

    Tarefa tarefa2 = new Tarefa();
    tarefa2.setDescricao("Lavar o carro");

    Tarefa tarefa3 = new Tarefa();
    tarefa3.setDescricao("Agendar o exame de saúde");
    tarefa3.setConcluida(true);

    checklist.addTarefa(tarefa1);
    checklist.addTarefa(tarefa2);
    checklist.addTarefa(tarefa3);
    List<Tarefa> listas = checklist.getTodasAsTarefasOrdenadasPorConclusao();

    assertEquals(3, listas.size());

    assertEquals("Agendar o exame de saúde", listas.get(0).getDescricao());
    assertTrue(listas.get(0).isConcluida());

    assertEquals("Ir ao mercado", listas.get(1).getDescricao());
    assertTrue(listas.get(1).isConcluida());

    assertEquals("Lavar o carro", listas.get(2).getDescricao());
    assertFalse(listas.get(2).isConcluida());
}
}