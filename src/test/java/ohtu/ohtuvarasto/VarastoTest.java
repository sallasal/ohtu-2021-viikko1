package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenOmallaTilavuudellaToimii() {
        Varasto isompiVarasto = new Varasto(20, 2);
        assertEquals(20, isompiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenOmallaSaldollaToimii() {
        Varasto isompiVarasto = new Varasto(20, 2);
        assertEquals(2, isompiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void asettaaNegatiivisenAlkusaldonOikein() {
        Varasto negAlkusaldo = new Varasto(2, -3);
        assertEquals(0, negAlkusaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tunnistaaTurhanVarastonYhdellaParametrilla() {
        Varasto turhaVarasto = new Varasto(-3);
        assertEquals(0, turhaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void tunnistaaTurhanVarastonKahdellaParametrilla() {
        Varasto turhaVarasto = new Varasto(-2, 0);
        assertEquals(0, turhaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void tayttaaVarastonJaHukkaaYlijaaman() {
        Varasto liianTaysi = new Varasto(10, 20);
        assertEquals(10, liianTaysi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenKasitellaanOikein() {
        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }
    
    @Test
    public void asettaaSaldonOikeinKunOttaaKaiken() {
        varasto.otaVarastosta(15);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kasitteleeLisayksenYlivuodonOikein() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kasitteleeNegLisayksenOikein() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-3);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void palauttaaOikeanStringin() {
        assertEquals("saldo = 100.0, vielä tilaa 10.0", varasto.toString());
    }

}