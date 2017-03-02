package de.usu.research.hobbit.gui.rabbitmq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.hobbit.core.Constants;
import org.junit.Assert;
import org.junit.Test;

import de.usu.research.hobbit.gui.rest.Datatype;
import de.usu.research.hobbit.gui.rest.beans.BenchmarkBean;
import de.usu.research.hobbit.gui.rest.beans.ConfigurationParamBean;
import de.usu.research.hobbit.gui.rest.beans.ConfigurationParamValueBean;
import de.usu.research.hobbit.gui.rest.beans.ExperimentBean;

public class ExperimentBeanTest {

    @Test
    public void testTransformation() {
        String experimentId = "LinkingExp10";
        // load the model
        Model model = AbstractRdfHelperTest.loadModel("de/usu/research/hobbit/gui/rabbitmq/experiment.ttl");
        ExperimentBean experiment = RdfModelHelper.createExperimentBean(model,
                model.getResource(Constants.EXPERIMENT_URI_NS + experimentId));

        Assert.assertEquals(experimentId, experiment.getId());
        // Check the system
        Assert.assertNotNull(experiment.getSystem());
        Assert.assertEquals("http://w3id.org/system#limesV1", experiment.getSystem().getId());
        Assert.assertEquals("Limes", experiment.getSystem().getName());
        Assert.assertEquals("Limes is an Instance Matching System...", experiment.getSystem().getDescription());
        // Check the benchmark
        Assert.assertNotNull(experiment.getBenchmark());
        Assert.assertEquals("http://w3id.org/bench#lance", experiment.getBenchmark().getId());
        Assert.assertEquals("Lance Benchmark", experiment.getBenchmark().getName());
        Assert.assertEquals(
                "LANCE is a benchmark for the assessment of Instance Matching techniques and systems for Linked Data data that are accompanied by a schema.",
                experiment.getBenchmark().getDescription());
        // Check the challenge task
        Assert.assertNotNull(experiment.getChallengeTask());
        Assert.assertEquals("http://example.org/MyChallengeTask1", experiment.getChallengeTask().getId());
        Assert.assertEquals("Example task 1", experiment.getChallengeTask().getName());
        Assert.assertEquals("Task 1 of my example challenge.", experiment.getChallengeTask().getDescription());
    }

    @Test
    public void testRealData() {
        String experimentId = "1487680214721";
        // load the model
        Model model = AbstractRdfHelperTest.loadModel("de/usu/research/hobbit/gui/rabbitmq/exampleExperiment.ttl");
        ExperimentBean experiment = RdfModelHelper.createExperimentBean(model,
                model.getResource(Constants.EXPERIMENT_URI_NS + experimentId));

        Assert.assertEquals(experimentId, experiment.getId());
        // Check the system
        Assert.assertNotNull(experiment.getSystem());
        Assert.assertEquals("http://w3id.org/hobbit/platform-benchmark/vocab#PlatformBenchmarkSystem",
                experiment.getSystem().getId());
        Assert.assertEquals("HOBBIT Platform Benchmark System", experiment.getSystem().getName());
        Assert.assertEquals("The system needed to run the platform benchmark.",
                experiment.getSystem().getDescription());
        // Check the benchmark
        Assert.assertNotNull(experiment.getBenchmark());
        Assert.assertEquals("http://w3id.org/hobbit/platform-benchmark/vocab#PlatformBenchmark",
                experiment.getBenchmark().getId());
        Assert.assertEquals("HOBBIT Platform Benchmark", experiment.getBenchmark().getName());
        Assert.assertEquals(
                "This benchmark emulates the traffic created by benchmarking a SPARQL store to determine the maximal throughput of the platform.",
                experiment.getBenchmark().getDescription());
        // Check the challenge task
        Assert.assertNull(experiment.getChallengeTask());
        // Check the status
        Assert.assertNull(experiment.getError());
        // Check the parameters
        BenchmarkBean benchmark = ((BenchmarkBean) experiment.getBenchmark());
        String labels[] = benchmark.getConfigurationParamNames().toArray(new String[0]);
        Arrays.sort(labels);
        Assert.assertArrayEquals(
                new String[] { "Number of data generators", "Number of queries", "Number of task generators", "Seed" },
                labels);
        benchmark.getConfigurationParams();

        Map<String, ConfigurationParamBean> expectedParameters = new HashMap<>();
        ConfigurationParamBean paramBean;
        paramBean = new ConfigurationParamBean("Number of data generators", Datatype.UNSIGNED_INT, true, "2");
        paramBean.setDescription("The number of data generators that will be created.");
        paramBean.setFeature(true);
        paramBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#numberOfDataGenerators");
        paramBean.setRange("http://www.w3.org/2001/XMLSchema#unsignedInt");
        expectedParameters.put(paramBean.getId(), paramBean);
        paramBean = new ConfigurationParamBean("Number of queries", Datatype.UNSIGNED_INT, true, "1000");
        paramBean.setDescription("The number of SPARQL queries that are sent.");
        paramBean.setFeature(true);
        paramBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#numberOfQueries");
        paramBean.setRange("http://www.w3.org/2001/XMLSchema#unsignedInt");
        expectedParameters.put(paramBean.getId(), paramBean);
        paramBean = new ConfigurationParamBean("Number of task generators", Datatype.UNSIGNED_INT, true, "1");
        paramBean.setDescription("The number of task generators that will be created.");
        paramBean.setFeature(true);
        paramBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#numberOfTaskGenerators");
        paramBean.setRange("http://www.w3.org/2001/XMLSchema#unsignedInt");
        expectedParameters.put(paramBean.getId(), paramBean);
        paramBean = new ConfigurationParamBean("Seed", Datatype.STRING, true, "31");
        paramBean.setDescription("The seed of the random number generators used by this bechmark.");
        paramBean.setFeature(false);
        paramBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#seed");
        paramBean.setRange("http://www.w3.org/2001/XMLSchema#integer");
        expectedParameters.put(paramBean.getId(), paramBean);

        checkConfigurationParameters(expectedParameters, benchmark.getConfigurationParams());
        
        // Check KPIs
        Map<String, ConfigurationParamValueBean> expectedKpis = new HashMap<>();
        ConfigurationParamValueBean kpiBean;
        kpiBean = new ConfigurationParamValueBean();
        kpiBean.setName("Queries per second");
        kpiBean.setDatatype(Datatype.DOUBLE);
        kpiBean.setDescription("Average number of processed queries (=result sets) that are received by the evaluation storage per second.");
        kpiBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#responsesPerSecond");
        kpiBean.setRange("http://www.w3.org/2001/XMLSchema#double");
        expectedKpis.put(kpiBean.getId(), kpiBean);
        kpiBean = new ConfigurationParamValueBean();
        kpiBean.setName("Average query runtime (in ms)");
        kpiBean.setDatatype(Datatype.DOUBLE);
        kpiBean.setDescription("The average time from the moment a query is sent to the benchmarked system until its response is received.");
        kpiBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#msPerQuery");
        kpiBean.setRange("http://www.w3.org/2001/XMLSchema#double");
        expectedKpis.put(kpiBean.getId(), kpiBean);
        kpiBean = new ConfigurationParamValueBean();
        kpiBean.setName("Query runtime standard deviation");
        kpiBean.setDatatype(Datatype.DOUBLE);
        kpiBean.setDescription("The standard deviation of the time between the moment a query is sent to the benchmarked system and the point in time at which its response is received.");
        kpiBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#queryRuntimeStdDev");
        kpiBean.setRange("http://www.w3.org/2001/XMLSchema#double");
        expectedKpis.put(kpiBean.getId(), kpiBean);
        kpiBean = new ConfigurationParamValueBean();
        kpiBean.setName("Error count");
        kpiBean.setDatatype(Datatype.INTEGER);
        kpiBean.setDescription("The number of errors identified by either missing expected responses or missing result sets or both.");
        kpiBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#errorCount");
        kpiBean.setRange("http://www.w3.org/2001/XMLSchema#int");
        expectedKpis.put(kpiBean.getId(), kpiBean);
        kpiBean = new ConfigurationParamValueBean();
        kpiBean.setName("Runtime");
        kpiBean.setDatatype(Datatype.STRING);
        kpiBean.setDescription("The overall runtime, i.e., the time from the first query sent to the last response received.");
        kpiBean.setId("http://w3id.org/hobbit/platform-benchmark/vocab#runtime");
        kpiBean.setRange("http://www.w3.org/2001/XMLSchema#integer");
        expectedKpis.put(kpiBean.getId(), kpiBean);
        
        checkKpis(expectedKpis, experiment.getKpis());
    }

    private void checkConfigurationParameters(Map<String, ConfigurationParamBean> expectedParameters,
            List<ConfigurationParamBean> configurationParams) {
        ConfigurationParamBean expected;
        for (ConfigurationParamBean paramBean : configurationParams) {
            Assert.assertTrue(
                    paramBean.getId() + " could not be found inside " + expectedParameters.keySet().toString(),
                    expectedParameters.containsKey(paramBean.getId()));
            expected = expectedParameters.get(paramBean.getId());
            Assert.assertEquals(paramBean.getId(), expected.getDefaultValue(), paramBean.getDefaultValue());
            Assert.assertEquals(paramBean.getId(), expected.getDescription(), paramBean.getDescription());
            Assert.assertEquals(paramBean.getId(), expected.getId(), paramBean.getId());
            Assert.assertEquals(paramBean.getId(), expected.getName(), paramBean.getName());
            Assert.assertEquals(paramBean.getId(), expected.getRange(), paramBean.getRange());
            Assert.assertEquals(paramBean.getId(), expected.getDatatype(), paramBean.getDatatype());
            Assert.assertEquals(paramBean.getId(), expected.getMax(), paramBean.getMax());
            Assert.assertEquals(paramBean.getId(), expected.getMin(), paramBean.getMin());
            Assert.assertEquals(paramBean.getId(), expected.getOptions(), paramBean.getOptions());
        }
        Assert.assertEquals(expectedParameters.size(), configurationParams.size());
    }

    private void checkKpis(Map<String, ConfigurationParamValueBean> expectedKpis,
            List<ConfigurationParamValueBean> kpis) {
        ConfigurationParamValueBean expected;
        for (ConfigurationParamValueBean paramBean : kpis) {
            Assert.assertTrue(
                    paramBean.getId() + " could not be found inside " + expectedKpis.keySet().toString(),
                    expectedKpis.containsKey(paramBean.getId()));
            expected = expectedKpis.get(paramBean.getId());
            Assert.assertEquals(paramBean.getId(), expected.getDescription(), paramBean.getDescription());
            Assert.assertEquals(paramBean.getId(), expected.getId(), paramBean.getId());
            Assert.assertEquals(paramBean.getId(), expected.getName(), paramBean.getName());
            Assert.assertEquals(paramBean.getId(), expected.getRange(), paramBean.getRange());
            Assert.assertEquals(paramBean.getId(), expected.getDatatype(), paramBean.getDatatype());
        }
        Assert.assertEquals(expectedKpis.size(), kpis.size());
    }

}