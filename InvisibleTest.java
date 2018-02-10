package org.gradle;

import org.junit.Test;

public class InvisibleTest {
    @Test
    public void canConstructAPersonWithAName() {
    	AmigoInvisible a = new AmigoInvisible("C:\\workspace\\AmigoInvisible\\participantes.txt");
    	a.ejecucion();
    }
}
