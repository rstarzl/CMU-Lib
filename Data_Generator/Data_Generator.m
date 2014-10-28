function [ data ] = Data_Generator( number_of_instances, number_of_features, distribution )

%default parameters
    if nargin < 2
        number_of_features = 2;
    end
    if nargin < 1
        number_of_instances = 500;
    end
% 
    Mu = rand(1,number_of_features);%Mean
    Sigma = rand(1,number_of_features);%Covariance Matrix
    data = mvnrnd(Mu,Sigma,number_of_instances);%generate data points
    
end

