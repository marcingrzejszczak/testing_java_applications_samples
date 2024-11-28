package com.example.week3.part1;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import com.tngtech.archunit.lang.ArchRule;

// TODO: Fix the class name
// TODO: AnalyzeClasses
public class ArchitectureHint {

	// TODO: Fix me
	@ArchTest
	static final ArchTests modelTests = null;

	// TODO: Fix me
	@ArchTest
	static final ArchTests repositoryTests = null;

	// TODO: Fix me
	@ArchTest
	static final ArchTests applierTests = null;

	// TODO: Fix me
	@ArchTest
	static final ArchTests layerTests = null;

	// TODO: Fix me
	@ArchTest
	static final ArchTests cycleTests = null;


	static class ModelTests {

		// TODO: Fix me
		@ArchTest
		public static final ArchRule modelClassesShouldBePublic = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule modelClassesShouldNotDependOnOtherPackages = null;
	}

	static class RepositoryTests {

		// TODO: Fix me
		@ArchTest
		public static final ArchRule repositoryCannotDependOnApplier = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule repositoryShouldHaveAProperSuffixAndResideInProperPackage = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule repositoryShouldHavePublicInterfaces = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule repositoryShouldHavePackagePrivateClasses = null;
	}

	static class ApplierTests {

		// TODO: Fix me
		@ArchTest
		public static final ArchRule applierCanDependOnModelAndRepository = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule appliersShouldHaveAProperSuffixAndResideInProperPackage = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule appliersShouldHavePublicInterfaces = null;

		// TODO: Fix me
		@ArchTest
		public static final ArchRule appliersShouldHavePackagePrivateClasses = null;
	}

	static class LayerTests {

		// TODO: Fix me
		@ArchTest
		public static final ArchRule layerTests = null;
	}

	static class CycleTests {

		// TODO: Fix me
		@ArchTest
		public static final ArchRule cycleTests = null;
	}

}
