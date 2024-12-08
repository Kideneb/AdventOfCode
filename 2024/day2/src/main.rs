use std::fs::read_to_string;

fn is_safe(report : Vec<i32>) -> bool {
    let descending = report.iter()
    .fold(1000, |prev,this| if *this < prev {*this} else{-1});
    let ascending = report.iter()
        .fold(-1, |prev,this| if *this > prev {*this} else{1000});
    let small_changes = report.iter()
        .fold(report[0] + 1, |prev, this| if (*this - prev).abs() <= 3 && (*this - prev).abs() >= 1  {*this} else {-5});
    (descending >= 0 || ascending <= 999) && (small_changes >= 0)
}

fn main() {
    let lines: Vec<Vec<i32>> = read_to_string("in.txt")
        .expect("Could not read from input")
        .lines()
        .map(|s: &str| -> Vec<i32> { String::from(s)
            .split(" ")
            .map(|t| String::from(t).parse().expect("Value not integer")).collect()
            })
        .collect();

    let mut counter1 = 0;
    let mut counter2 = 0;
    for line in lines {
        let l = line.len();
        let mut is_safe_enough = false;
        if is_safe(line.clone()) {counter1 += 1; is_safe_enough = true};
        for i in 0..l {
            if is_safe([&line[0..i], &line[i+1..l]].concat()) {
                is_safe_enough = true;
            }
        }
        if is_safe_enough {
            counter2 += 1;
        }
    }
    println!("{counter1}");
    println!("{counter2}");
}
