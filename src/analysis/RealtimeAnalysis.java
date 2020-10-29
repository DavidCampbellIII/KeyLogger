package analysis;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Random;

public class RealtimeAnalysis {
	private ArrayList<Attribute> categories;
	private Instances data;
	private LinearRegression classifier;
	private Evaluation evaluation;

	public RealtimeAnalysis()
	{
		classifier = new LinearRegression();
		categories = new ArrayList<>();
		categories.add(new Attribute("Key Down Time"));
		categories.add(new Attribute("Timestamp"));
		data = new Instances("Runtime dataset", categories, 0);
		data.setClassIndex(data.numAttributes() - 1);
	}

	public void addData(double keyTimeDown, double timestampRelease)
	{
		double[] vals = new double[data.numAttributes()];
		vals[0] = keyTimeDown;
		vals[1] = timestampRelease;
		Instance newInstance = new DenseInstance(1.0, vals);
		newInstance.setDataset(data);
		data.add(newInstance);
	}

	public void evaluate()
	{
		try
		{
			classifier.buildClassifier(data);
			System.out.println(classifier);
		}
		catch(Exception e)
		{
			System.out.println("==ERROR WHILE BUILDING CLASSIFIER==");
			e.printStackTrace();
		}

//		try
//		{
//			evaluation = new Evaluation(data);
//		}
//		catch(Exception e)
//		{
//			System.out.println("==ERROR IN CREATING EVALUATION OBJECT==");
//			e.printStackTrace();
//		}
//
//		try
//		{
//			evaluation.crossValidateModel(classifier, data, 10, new Random(1));
//			System.out.println(evaluation.toSummaryString("Results\n", false));
//		}
//		catch(Exception e)
//		{
//			System.out.println("==ERROR WHILE EVALUATING==");
//			e.printStackTrace();
//		}
	}
}
