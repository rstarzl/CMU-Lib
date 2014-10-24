function [ ] = Pow_Law_Drawing( sorted )
%POW_LAW_DRAWING Summary of this function goes here
%   Detailed explanation goes here
x = 1:length(sorted);
plot(log(sorted),log(x));

end

