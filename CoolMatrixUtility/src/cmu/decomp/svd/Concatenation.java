package cmu.decomp.svd;
import cmu.core.Mat;

public interface Concatenation {
	public Mat concat(Mat[] SplitedMatrix);
}
