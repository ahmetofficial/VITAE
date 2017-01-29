/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DRUGS', {
    drug_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    commercial_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    chemical_name: {
      type: DataTypes.STRING,
      allowNull: true
    },
    type_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'TYPES_OF_DRUGS',
        key: 'type_id'
      }
    },
    prescription_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PRESCRIPTION_TYPE',
        key: 'prescription_type_id'
      }
    },
    form_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'FORM_OF_DRUGS',
        key: 'form_id'
      }
    },
    company_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DRUG_COMPANIES',
        key: 'company_id'
      }
    },
    general_descriptions: {
      type: DataTypes.STRING,
      allowNull: true
    }
  }, {
    tableName: 'DRUGS'
  });
};
