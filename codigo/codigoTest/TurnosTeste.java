import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TurnosTeste {

    @Test
    public void testGetNome() {
        assertEquals("Manhã", Turnos.MANHA.getNome());
        assertEquals("Tarde", Turnos.TARDE.getNome());
        assertEquals("Noite", Turnos.NOITE.getNome());
    }

    @Test
    public void testGetInicio() {
        assertEquals(LocalTime.of(8, 0), Turnos.MANHA.getInicio());
        assertEquals(LocalTime.of(12, 1), Turnos.TARDE.getInicio());
        assertEquals(LocalTime.of(18, 1), Turnos.NOITE.getInicio());
    }

    @Test
    public void testGetFim() {
        assertEquals(LocalTime.of(12, 0), Turnos.MANHA.getFim());
        assertEquals(LocalTime.of(18, 0), Turnos.TARDE.getFim());
        assertEquals(LocalTime.of(23, 59), Turnos.NOITE.getFim());
    }

    @Test
    public void testDentroDoTurno() {
        assertTrue(Turnos.MANHA.dentroDoTurno(LocalTime.of(8, 0)));
        assertTrue(Turnos.MANHA.dentroDoTurno(LocalTime.of(10, 0)));
        assertFalse(Turnos.MANHA.dentroDoTurno(LocalTime.of(12, 0)));

        assertTrue(Turnos.TARDE.dentroDoTurno(LocalTime.of(12, 1)));
        assertTrue(Turnos.TARDE.dentroDoTurno(LocalTime.of(15, 0)));
        assertFalse(Turnos.TARDE.dentroDoTurno(LocalTime.of(18, 0)));

        assertTrue(Turnos.NOITE.dentroDoTurno(LocalTime.of(18, 1)));
        assertTrue(Turnos.NOITE.dentroDoTurno(LocalTime.of(21, 0)));
        assertFalse(Turnos.NOITE.dentroDoTurno(LocalTime.of(23, 59)));
    }
}
