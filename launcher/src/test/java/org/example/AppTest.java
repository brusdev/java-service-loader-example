package org.example;

import org.example.plugin.PluginProvider;
import org.example.plugin.hash.HashPlugin;
import org.example.plugin.hash.SimpleHashPlugin;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final String TEST_TEXT = "TEST";

    @Test
    public void testSimpleHashPlugin()
    {
        HashPlugin hashPlugin = PluginProvider.getInstance().create(SimpleHashPlugin.NAME);

        Assert.assertNotNull(hashPlugin);

        Assert.assertEquals(TEST_TEXT.hashCode(), hashPlugin.hash(TEST_TEXT));
    }
}
