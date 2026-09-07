data class Student(
    val id: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
) {
    
    val status: String
        get() = if (gpa >= 4.0) "Pass" else "Fail"

    override fun toString(): String {
        return String.format("Ma SV: %-5s | Ten: %-20s | Tuoi: %-2d | Nganh: %-20s | GPA: %-4.1f | %s",
            id, fullName, age, major, gpa, status)
    }
}

fun main() {
    val students = mutableListOf(
        Student("SV01", "Huynh Ngoc Anh", 21, "Cong nghe thong tin", 8.5),
        Student("SV02", "Tran Thi Mai", 20, "Marketing", 3.8), 
        Student("SV03", "Le Bao Binh", 22, "Cong nghe thong tin", 9.2),
        Student("SV04", "Nguyen Van Dat", 24, "Thiet ke", 7.5),
        Student("SV05", "Pham Truc Nhan", 21, "Cong nghe thong tin", 8.0)
    )

    println("========== STUDENT MANAGEMENT ==========")
    println("1. Add student")
    println("2. Display all students")
    println("3. Search student")
    println("4. Calculate average GPA")
    println("5. Find student with highest GPA")
    println("6. Remove student")
    println("0. Exit")
    println("========================================")
    println("Choose: 7 \n")

    println("========== DANH SACH SINH VIEN BAN DAU ==========")
    students.forEach { println(it) }

    println("\n========== THUC THI 12 YEU CAU ==========")
    
    // 1. Dem so sinh vien co GPA >= 8.0
    val countGpaGte8 = students.count { it.gpa >= 8.0 }
    println("1. So sinh vien co GPA >= 8.0: $countGpaGte8")

    // 2. Dem so sinh vien co GPA < 5.0
    val countGpaLt5 = students.count { it.gpa < 5.0 }
    println("2. So sinh vien co GPA < 5.0: $countGpaLt5")

    // 3. Tinh GPA trung binh cua sinh vien nganh duoc giao
    val targetMajor = "Cong nghe thong tin"
    val avgGpa = students.filter { it.major.equals(targetMajor, ignoreCase = true) }
        .map { it.gpa }
        .average()
    System.out.printf("3. GPA trung binh nganh %s: %.2f\n", targetMajor, if (avgGpa.isNaN()) 0.0 else avgGpa)

    // 4. Tim sinh vien co GPA cao nhat (Dung maxBy cho Kotlin < 1.4)
    println("\n4. Sinh vien co GPA cao nhat:")
    students.maxBy { it.gpa }?.let { println(it) }

    // 5. Tim sinh vien lon tuoi nhat (Dung maxBy cho Kotlin < 1.4)
    println("\n5. Sinh vien lon tuoi nhat:")
    students.maxBy { it.age }?.let { println(it) }

    // 6. Tim sinh vien co GPA nam trong khoang 7.0 -> 8.5
    println("\n6. Sinh vien co GPA tu 7.0 den 8.5:")
    students.filter { it.gpa in 7.0..8.5 }.forEach { println(it) }

    // 7. Tim tat ca sinh vien thuoc mot nganh
    println("\n7. Danh sach sinh vien nganh $targetMajor:")
    students.filter { it.major.equals(targetMajor, ignoreCase = true) }.forEach { println(it) }

    // 8. Tim sinh vien theo mot phan ten
    val searchKeyword = "Anh"
    println("\n8. Tim sinh vien co chua ten '$searchKeyword':")
    students.filter { it.fullName.contains(searchKeyword, ignoreCase = true) }.forEach { println(it) }

    // 9. Sap xep sinh vien theo GPA giam dan
    println("\n9. Sap xep sinh vien theo GPA giam dan:")
    students.sortedByDescending { it.gpa }.forEach { println(it) }

    // 10. Hien thi 3 sinh vien co GPA cao nhat
    println("\n10. Top 3 sinh vien co GPA cao nhat:")
    students.sortedByDescending { it.gpa }.take(3).forEach { println(it) }

    // 11. Sap xep sinh vien theo tuoi (tang dan)
    println("\n11. Sap xep sinh vien theo tuoi:")
    students.sortedBy { it.age }.forEach { println(it) }

    // 12. Sap xep sinh vien theo ten (Alpha B)
    println("\n12. Sap xep sinh vien theo ten:")
    students.sortedBy { it.fullName }.forEach { println(it) }
    
    //13. Tim sinh vien theo ma sinh vien
    println("\n13. Tim sinh vien theo ma sinh vien (id: SV02):")
    students.filter { it.id == "SV02" }.forEach { println(it) }

    //14. Thong ke pass / Fail
    val (passed, failed) = students.partition { it.gpa >= 4.0 }
    println("\n14. Thong ke sinh vien pass/fail:")
    println("-> DANH SACH PASS (${passed.size} sinh vien):")
    passed.forEach { println("   + ${it.fullName} (GPA: ${it.gpa})") }

    println("\n-> DANH SACH FAIL (${failed.size} sinh vien):")
    failed.forEach { println("   + ${it.fullName} (GPA: ${it.gpa})") }
    // 15. Kiem tra tinh hop le cho toan bo danh sach
    println("\n15. Kiem tra tinh hop le cua toan bo danh sach:")
    
    // Su dung 'all' de kiem tra xem TAT CA sinh vien co dat chuan khong
    val isAllValid = students.all { it.gpa in 0.0..10.0 && it.age > 0 }
    println("-> Toan bo danh sach hop le: $isAllValid")

    // Su dung 'filterNot' de tim ra nhung sinh vien vi pham dieu kien
    val invalidStudents = students.filterNot { it.gpa in 0.0..10.0 && it.age > 0 }
    
    if (invalidStudents.isEmpty()) {
        println("-> Khong co sinh vien nao bi loi du lieu.")
    } else {
        println("-> PHAT HIEN ${invalidStudents.size} sinh vien co du lieu KHONG HOP LE:")
        invalidStudents.forEach { 
            println("   + ${it.fullName} (GPA: ${it.gpa}, Tuoi: ${it.age})") 
        }
    }
}