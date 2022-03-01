Fork https://github.com/Kotlin-Polytech/KotlinAsFirst2020 then git clone (https://github.com/VIN0GR4D/KotlinAsFirst2020.git)  
cd KotlinAsFirst2020  
git remote add upstream-my https://github.com/VIN0GR4D/KotlinAsFirst2021.git  
git fetch upstream-my  
git checkout backport  
git cherry-pick d535f359...FETCH_HEAD  
git remote add upstream-theirs https://github.com/MikleAKA/KotlinAsFirst2021.git  
git fetch upstream-theirs  
git checkout master  
git merge backport upstream-theirs/master  
git status -> have some conflicts  
solve conflicts, then git status to check that everything is solved  
git add *  
git merge --continue  
type nul > remotes  
git remote -v  
git add *  
git commit -m "No conflicts at the moment"  
git push  
type nul > howto.md  
git add *  
git commit -m "done"  
git push  
