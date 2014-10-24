%generate 500 instances with 2 features
data = Data_Generator(10000,20000 );

[M, N] = size(data);
distances = zeros(1, M*M);

%generate and normalize random weight for each features
weight = normc(rand(1,N));
weight_matrix = repmat(weight,M,1);
weigted_data = data .* weight_matrix;

%I think there should be a way to optimize this part, just don't know how.
%will come back for optimization later
for i = 1:M
    for j = 1:M
        distances((i-1)*M + j) = norm(weigted_data(i)-weigted_data(j));
    end
end
sorted = sort(distances);


%T=clusterdata(weigted_data,'cutoff',100,'distance' , 1/8);
%show the first two demision in 2d plate
%scatter(weigted_data(:,1),weigted_data(:,2));

%
%Pow_Law_Drawing(sorted);

%assign labels
labels = Label_generator(data, 5);
%[U,S,V] = svd(data);

%scatter(weigted_data(labels==1,1),weigted_data(labels==1,2),'r');
%scatter(weigted_data(labels==2,1),weigted_data(labels==2,2),'b');



%scatter(weigted_data(:,1),weigted_data(:,2));



