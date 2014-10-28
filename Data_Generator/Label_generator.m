function [ lables ] = Label_generator( data, num_of_labels )
%ASSIGN_LABEL_LINER Summary of this function goes here
%   Detailed explanation goes here
    
    if nargin < 2
        num_of_labels = 2;
    end
    lables = kmeans(data,num_of_labels);
    
end

