function svd2()
    S = load('data.txt');
    
    [L1, L2] = master(S);
    L1
    L2
    [U, ~, ~] = svd(S);
    U
    
    
end

function [L1, L2] = master(S) 
    col_size = size(S, 1);
    L1 = rand(col_size, 1);
    L2 = rand(col_size, 1);
    slave_num = 2;
    slave_size = size(S,2)/slave_num;
    L1_rec = zeros(col_size, slave_num);
    L2_rec = zeros(col_size, slave_num);
    e = 1000;
    e_new = 0;
    thr = 1e-4;
    while (abs(e - e_new) > thr) 
        for i = 1:slave_num
            start = 1 + (i-1)*slave_size;
            [L1_i, L2_i] = slave(L1, L2, S(:, start: start + slave_size - 1));
            L1_rec(:, i) = L1_i;
            L2_rec(:, i) = L2_i;
        end
        % update L1, L2
        L1 = mean(L1_rec, 2);
        L2 = mean(L2_rec, 2);
        e = e_new;
        e_new = S - L1*(S'*L1)' - L2*(S'*L2)';
    end
    
end


function [L1, L2] = slave(L1, L2, S) 
    e = 1000;
    e_new = 0;
    thr = 1e-4;
    while (abs(e - e_new) > thr) 
        L1_new = S*S'*L1;
        L1_new = L1_new / norm(L1_new);
        S_new = S - L1_new*(S'*L1_new)';
        L2_new = S_new*S_new'*L2;
        L2_new = L2_new / norm(L2_new);
        % update 
        e = e_new;
        e_new = S - L1_new*(S'*L1_new)' - L2_new*(S'*L2_new)';
        L1 = L1_new;
        L2 = L2_new;
    end
    
end