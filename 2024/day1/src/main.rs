use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let file_path: &str = "in.txt";

    let file = File::open(file_path).expect("File not found");
    let lines = io::BufReader::new(file).lines();
    let mut v1: Vec<i32> = Vec::new();
    let mut v2: Vec<i32> = Vec::new();

    for line in lines.flatten() {
        let entries: Vec<&str> = line.split("   ").collect();
        v1.push(entries[0].parse().expect("not int"));
        v2.push(entries[1].parse().expect("not int"));
    }

    v1.sort();
    v2.sort();
    let mut distance = 0;
    for i in 0..v1.len() {
        distance += (v1[i] - v2[i]).abs();
    }

    println!("{distance}");

    let mut similarity = 0;
    let mut index = 0;
    for i in 0..v1.len() {
        if index >= v1.len() {
            break;
        }
        while v2[index] < v1[i] {
            index += 1;
            if index >= v1.len() {
                break;
            }
        }
        if index >= v1.len() {
            break;
        }
        while v2[index] == v1[i] {
            similarity += v1[i];
            index += 1;
            if index >= v1.len() {
                break;
            }
        }
    }
    println!("{similarity}")
}