package org.dynjs.runtime.builtins;

import org.dynjs.runtime.AbstractDynJSTestSupport;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test basic require() functionality.
 */
public class RequireTest extends AbstractDynJSTestSupport {

    @Test
    public void testRequirePaths() {
        assertThat(eval("require.paths")).isInstanceOf(ArrayList.class);
    }
}
