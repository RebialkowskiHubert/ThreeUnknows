package application;

import static org.jenetics.engine.EvolutionResult.toBestPhenotype;
import static org.jenetics.engine.limit.bySteadyFitness;
import org.jenetics.engine.*;
import org.jenetics.util.DoubleRange;
import org.jenetics.*;

public class Funkcja
{
	String stat, wx, wy, wmin;
	static String[] funkcje={
		"(sin(x)+sin(x-y)-cos(x)*tan(y))/(sqrt(pow((x-y),2)+pow((x+y),2))",
		"(tan(x)+cos(x-y)-sin(x)*cos(y))/(sqrt(pow((x+y),2)+pow((x-y),2))",
		"(cos(x)+tan(x+y)-tan(x)*sin(y))/(sqrt(pow((x-y),2)+pow((x+y),2))"
	};
	static String opc;
	
	static double x, y;
	double x1, x2;
	
	static int flag;
	
	private static double fitness(final double[] wartosc)
	{
		x=wartosc[0];
		y=wartosc[1];
		if(opc.equals(funkcje[0]))
		{
			flag=1;
			return (Math.sin(x)+Math.sin(x-y)-Math.cos(x)*Math.tan(y)/Math.sqrt(Math.pow((x-y),2)+Math.pow((x+y),2)));
		}
		if(opc.equals(funkcje[1]))
		{
			flag=2;
			return (Math.tan(x)+Math.cos(x-y)-Math.sin(x)*Math.cos(y)/Math.sqrt(Math.pow((x+y),2)+Math.pow((x-y),2)));
		}
		if(opc.equals(funkcje[2]))
		{
			flag=3;
			return (Math.cos(x)+Math.tan(x+y)-Math.tan(x)*Math.sin(y)/Math.sqrt(Math.pow((x-y),2)+Math.pow((x+y),2)));
		}
		else
		{
			flag=0;
			return 0;
		}
	}
	
	public void oblicz(int popsize, double mut, double cross, int limit)
	{
		final Engine<DoubleGene, Double> engine=Engine
			.builder(
				Funkcja::fitness,
				codecs.ofVector(DoubleRange.of(x1, x2), 2))
			.populationSize(popsize)
			.optimize(Optimize.MINIMUM)
			.alterers(
				new Mutator<>(mut),
				new SinglePointCrossover<>(cross))
			.build();
		
		
		final EvolutionStatistics<Double, ?>
		statystyka=EvolutionStatistics.ofNumber();
		
		final Phenotype<DoubleGene, Double> najlepszy=engine.stream()
				.limit(bySteadyFitness(7))
				.limit(limit)
				.peek(statystyka)
				.collect(toBestPhenotype());		

		stat=statystyka.toString();
		String best=najlepszy.toString();
		
		String tmp=best.replace("[", "");
		tmp=tmp.replace("]", "");
		String[] tab=tmp.split(",");
		wx=tab[0];
		String[] tab2=tab[1].split("-->");
		wy=tab2[0];
		wmin=String.valueOf(najlepszy.getFitness());
	}
}