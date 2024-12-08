use regex::Regex;

fn main() {
    let reg = Regex::new(r"mul\((\d+)\,(\d+)\)|(do\(\))|(don\'t\(\))").expect("Not a regex :(");
    let string = std::fs::read_to_string("in.txt")
        .expect("Could not read input");

    let mut total_sum = 0;
    let mut conditional_sum = 0;
    let mut sum_currently = true;
    for c in reg.captures_iter(&string) {
        if c.get(3) != None {
            sum_currently = true;
        } else if c.get(4) != None {
            sum_currently = false;
        } else {
            let a = c.get(1).unwrap().as_str();
            let b = c.get(2).unwrap().as_str();
            let product = a.parse::<i32>().expect("Not an int") * b.parse::<i32>().expect("Not an int"); 
            total_sum += product;
            if sum_currently {conditional_sum += product;}
        }
    }
    
    println!("{total_sum}");
    println!("{conditional_sum}");
}
